from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import RecipeViewSet

router = DefaultRouter()
router.register(r'recipes', RecipeViewSet)

urlpatterns = [
    path('api/', include(router.urls)),
    #path('api/categories/', CategoryListView.as_view(), name='category-list'),
]
# from django.urls import path, include
# from rest_framework.routers import DefaultRouter
# from .views import RecipeViewSet

# router = DefaultRouter()
# router.register(r'recipes', RecipeViewSet)

# urlpatterns = [
#     path('', include(router.urls)),
#     path('recipes/<int:pk>/', RecipeViewSet.as_view({'get': 'custom_action'}), name='custom-action')    
# ]
