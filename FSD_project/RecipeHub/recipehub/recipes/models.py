# models.py
from django.db import models
from django.contrib.auth import get_user_model

User = get_user_model()

class Recipe(models.Model):
    title = models.CharField(max_length=200)
    description = models.TextField()
    ingredients = models.TextField()
    instructions = models.TextField()
    video_link = models.URLField(blank=True)
    photos = models.ImageField(upload_to='recipe_photos/')
    category = models.TextField(blank=True)
    

    def __str__(self):
        return self.title
