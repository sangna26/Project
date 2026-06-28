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
def load_image(path, resize=(100, 100)):
    img = cv2.imread(path)
    if img is None:
        raise ValueError("Image not found!")
    img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
    img = cv2.resize(img, resize)
    return img


# --------------------------------------------------
# Clustering Functions (ALL FIXED)
# --------------------------------------------------
def kmeans_cluster(data, k):
    model = KMeans(n_clusters=k, random_state=42)
    labels = model.fit_predict(data)
    return model.cluster_centers_[labels]


def minibatch_kmeans(data, k):
    model = MiniBatchKMeans(n_clusters=k, random_state=42)
    labels = model.fit_predict(data)
    return model.cluster_centers_[labels]


def agglomerative_cluster(data, k):
    model = AgglomerativeClustering(n_clusters=k)
    labels = model.fit_predict(data)
    centers = np.array([data[labels == i].mean(axis=0) for i in range(k)])
    return centers[labels]


def mean_shift_cluster(data):
    model = MeanShift(bin_seeding=True)
    labels = model.fit_predict(data)
    return model.cluster_centers_[labels]


def dbscan_cluster(data):
    model = DBSCAN(eps=20, min_samples=100)
    labels = model.fit_predict(data)
    result = data.copy()
    result[labels == -1] = [0, 0, 0]   # noise as black
    return result


def spectral_cluster(data, k):
    model = SpectralClustering(
        n_clusters=k,
        assign_labels='kmeans',
        random_state=42
    )
    labels = model.fit_predict(data)
    centers = np.array([data[labels == i].mean(axis=0) for i in range(k)])
    return centers[labels]


def gmm_cluster(data, k):
    model = GaussianMixture(n_components=k, random_state=42)
    labels = model.fit_predict(data)
    return model.means_[labels]


# --------------------------------------------------
# Main Controller
# --------------------------------------------------
def image_clustering(image_path):
    img = load_image(image_path)
    h, w, c = img.shape
    data = img.reshape((-1, 3)).astype(np.float32)
    titles = []
    images = []

    print("""
Choose Clustering Method:
1. K-Means
2. MiniBatch K-Means
3. Agglomerative
4. Mean Shift
5. DBSCAN
6. Spectral
7. Gaussian Mixture Model
8. Compare ALL Methods
""")

    choice = int(input("Enter choice (1-8): "))

    if choice in [1, 2, 3,4,5, 6, 7, 8]:
        k = int(input("Enter number of clusters: "))

    # ---------------- SINGLE METHOD ----------------
    # ---------------- SINGLE METHOD ----------------
        if choice == 1:
            titles = ["Original", "K-Means"]
            images = [
                img.reshape((-1, 3)),
                kmeans_cluster(data, k)
            ]

        elif choice == 2:
            titles = ["Original", "MiniBatch K-Means"]
            images = [
                img.reshape((-1, 3)),
                minibatch_kmeans(data, k)
            ]

        elif choice == 3:
            titles = ["Original", "Agglomerative"]
            images = [
                img.reshape((-1, 3)),
                agglomerative_cluster(data, k)
            ]

        elif choice == 4:
            titles = ["Original", "Mean Shift"]
            images = [
                img.reshape((-1, 3)),
                mean_shift_cluster(data)
            ]

        elif choice == 5:
            titles = ["Original", "DBSCAN"]
            images = [
                img.reshape((-1, 3)),
                dbscan_cluster(data)
            ]

        elif choice == 6:
            titles = ["Original", "Spectral"]
            images = [
                img.reshape((-1, 3)),
                spectral_cluster(data, k)
            ]

        elif choice == 7:
            titles = ["Original", "GMM"]
            images = [
                img.reshape((-1, 3)),
                gmm_cluster(data, k)
            ]


    # ---------------- OPTION 8 : COMPARE ALL ----------------
        
        elif choice == 8:
            print("""
    Select methods to compare (comma separated):
    0 = Original
    1 = K-Means
    2 = MiniBatch K-Means
    3 = Agglomerative
    4 = Mean Shift
    5 = DBSCAN
    6 = Spectral
    7 = GMM
    """)

            selected = list(map(int, input("Enter choices (e.g. 0,1,3): ").split(",")))

            titles = ["Original"]
            images = [img.reshape((-1, 3))]

            for ch in selected:
                if ch == 0:
                    titles.append("Original")
                    images.append(img.reshape((-1, 3)))

                elif ch == 1:
                    titles.append("K-Means")
                    images.append(kmeans_cluster(data, k))

                elif ch == 2:
                    titles.append("MiniBatch")
                    images.append(minibatch_kmeans(data, k))

                elif ch == 3:
                    titles.append("Agglomerative")
                    images.append(agglomerative_cluster(data, k))

                elif ch == 4:
                    titles.append("Mean Shift")
                    images.append(mean_shift_cluster(data))

                elif ch == 5:
                    titles.append("DBSCAN")
                    images.append(dbscan_cluster(data))

                elif ch == 6:
                    titles.append("Spectral")
                    images.append(spectral_cluster(data, k))

                elif ch == 7:
                    titles.append("GMM")
                    images.append(gmm_cluster(data, k))
                else:
                    raise ValueError("Invalid choice")


        else:
            raise ValueError("Invalid choice")

    # --------------------------------------------------
    # Display
    # --------------------------------------------------
    plt.figure(figsize=(16, 8))

    for i, (title, img_data) in enumerate(zip(titles, images)):
        plt.subplot(2, 4, i + 1)
        plt.title(title)
        plt.axis("off")

        clustered_img = img_data.reshape((h, w, 3)).astype(np.uint8)
        plt.imshow(clustered_img)

    plt.tight_layout()
    plt.show()


# --------------------------------------------------
# Run
# --------------------------------------------------
if __name__ == "__main__":
    image_path = "image.jpg"   # change if needed
    image_clustering(image_path)
