from rest_framework import serializers
from .models import Recipe

class RecipeSerializer(serializers.ModelSerializer):
    username = serializers.ReadOnlyField(source='user.username')
    email = serializers.ReadOnlyField(source='user.email')

    class Meta:
        model = Recipe
        fields = [ 'id','title', 'description', 'ingredients', 'instructions', 'video_link', 'photos', 'category', 'username', 'email']


