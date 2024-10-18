from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import CustomUserViewSet

router = DefaultRouter()
router.register(r'users', CustomUserViewSet)

urlpatterns = [
    path('', include(router.urls)),
    path('login/', CustomUserViewSet.as_view({'post': 'login'}), name='login'),
    path('signup/', CustomUserViewSet.as_view({'post': 'signup'}), name='signup'),
    path('users/<int:pk>/user_status/', CustomUserViewSet.as_view({'get': 'user_status'}), name='user-status'),
    path('logout/', CustomUserViewSet.as_view({'post': 'logout'}), name='logout'),  # Added logout endpoint
]
