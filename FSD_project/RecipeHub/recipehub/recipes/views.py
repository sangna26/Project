from django.shortcuts import render
from rest_framework import viewsets
from .models import Recipe
from .serializers import RecipeSerializer
from django_filters.rest_framework import DjangoFilterBackend

class RecipeViewSet(viewsets.ModelViewSet):
    queryset = Recipe.objects.all()
    serializer_class = RecipeSerializer
    filter_backends = [DjangoFilterBackend]
    filterset_fields = ['category']

    def get_queryset(self):
        queryset = super().get_queryset()
        category = self.request.query_params.get('category')
        
        if category and category.lower() != 'all':
            queryset = queryset.filter(category__iexact=category)
        
        return queryset


