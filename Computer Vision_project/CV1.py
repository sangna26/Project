import cv2
import numpy as np
import matplotlib.pyplot as plt

from sklearn.cluster import (
    KMeans,
    MiniBatchKMeans,
    AgglomerativeClustering,
    MeanShift,
    DBSCAN,
    SpectralClustering
)
from sklearn.mixture import GaussianMixture


# --------------------------------------------------
# Image Loader
# --------------------------------------------------
def load_image(path, resize=(200, 200)):
    img = cv2.imread(path)
    if img is None:
        raise ValueError("Image not found!")
    img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
    img = cv2.resize(img, resize)
    return img


# --------------------------------------------------
# Clustering Functions
# --------------------------------------------------
def kmeans_cluster(data, k):
    model = KMeans(n_clusters=k, random_state=42)
    labels = model.fit_predict(data)
    centers = model.cluster_centers_
    return centers[labels]


def minibatch_kmeans(data, k):
    model = MiniBatchKMeans(n_clusters=k, random_state=42)
    labels = model.fit_predict(data)
    return model.cluster_centers_[labels]


def agglomerative_cluster(data, k):
    model = AgglomerativeClustering(n_clusters=k)
    labels = model.fit_predict(data)
    return data.groupby(labels, axis=0).mean().values[labels]


def mean_shift_cluster(data):
    model = MeanShift(bin_seeding=True)
    labels = model.fit_predict(data)
    return model.cluster_centers_[labels]


def dbscan_cluster(data):
    model = DBSCAN(eps=20, min_samples=100)
    labels = model.fit_predict(data)
    result = data.copy()
    result[labels == -1] = [0, 0, 0]  # noise
    return result


def spectral_cluster(data, k):
    model = SpectralClustering(n_clusters=k, assign_labels='kmeans')
    labels = model.fit_predict(data)
    return data.groupby(labels, axis=0).mean().values[labels]


def gmm_cluster(data, k):
    model = GaussianMixture(n_components=k)
    labels = model.fit_predict(data)
    return model.means_[labels]


# --------------------------------------------------
# Main Controller
# --------------------------------------------------
def image_clustering(image_path):
    img = load_image(image_path)
    h, w, c = img.shape
    data = img.reshape((-1, 3)).astype(np.float32)

    print("""
Choose Clustering Method:
1. K-Means
2. MiniBatch K-Means
3. Agglomerative
4. Mean Shift
5. DBSCAN
6. Spectral
7. Gaussian Mixture Model
""")

    choice = int(input("Enter choice (1-7): "))

    if choice in [1, 2, 3, 6, 7]:
        k = int(input("Enter number of clusters: "))

    if choice == 1:
        clustered = kmeans_cluster(data, k)
    elif choice == 2:
        clustered = minibatch_kmeans(data, k)
    elif choice == 3:
        clustered = agglomerative_cluster(data, k)
    elif choice == 4:
        clustered = mean_shift_cluster(data)
    elif choice == 5:
        clustered = dbscan_cluster(data)
    elif choice == 6:
        clustered = spectral_cluster(data, k)
    elif choice == 7:
        clustered = gmm_cluster(data, k)
    else:
        raise ValueError("Invalid choice")

    clustered_img = clustered.reshape((h, w, 3)).astype(np.uint8)

    # --------------------------------------------------
    # Display
    # --------------------------------------------------
    plt.figure(figsize=(12, 5))
    plt.subplot(1, 2, 1)
    plt.title("Original Image")
    plt.imshow(img)
    plt.axis("off")

    plt.subplot(1, 2, 2)
    plt.title("Clustered Image")
    plt.imshow(clustered_img)
    plt.axis("off")

    plt.show()


# --------------------------------------------------
# Run
# --------------------------------------------------
if __name__ == "__main__":
    image_path = "image.jpg"  # <-- change path
    image_clustering(image_path)
