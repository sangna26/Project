import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../styles/Recipes.css';
import RecipeCard from '../components/RecipeCard'; // Import RecipeCard

const Recipes = () => {
  const [recipes, setRecipes] = useState([]);
  const [categories, setCategories] = useState(['all', 'Mexican', 'Italian Cuisine', 'Chinese', 'Indian Food']); // Example categories
  const [selectedCategory, setSelectedCategory] = useState('all'); // Default to 'all'

  // Fetch recipes based on selected category
  useEffect(() => {
    const fetchRecipes = async () => {
      try {
        const response = await axios.get('http://localhost:8000/api/recipes/', {
          params: {
            category: selectedCategory === 'all' ? undefined : selectedCategory,
          },
        });
        setRecipes(response.data);
      } catch (error) {
        console.error('Error fetching recipes:', error);
      }
    };

    fetchRecipes();
  }, [selectedCategory]); // Re-fetch recipes when selectedCategory changes

  // Fetch categories on component mount
  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const response = await axios.get('http://localhost:8000/api/categories/');
        setCategories(response.data);
      } catch (error) {
        console.error('Error fetching categories:', error);
      }
    };

    fetchCategories();
  }, []); // Only run once when the component mounts

  // Handle category change
  const handleCategoryChange = (category) => {
    setSelectedCategory(category);
  };

  return (
    <div className="recipes">
      <h1>Recipes</h1>

      {/* Filter by category */}
      <div className="category-filter">
        {categories.map((category, index) => (
          <button
            key={index}
            onClick={() => handleCategoryChange(category)}
            className={selectedCategory === category ? 'active' : ''}
          >
            {category}
          </button>
        ))}
      </div>

      {/* Display filtered recipes */}
      <div className="recipe-list">
      <p>{recipes.id}</p>
        {recipes.length > 0 ? (
          
          recipes.map((recipe) => (
            
            <RecipeCard key={recipe.id} recipe={recipe} /> // Use RecipeCard component to display each recipe
          ))
        ) : (
          <p>No recipes found for the selected filters.</p>
        )}
      </div>
    </div>
  );
};

export default Recipes;
