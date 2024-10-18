import React from 'react';
import { Link } from 'react-router-dom'; // Import Link
import '../styles/RecipeCard.css';

const RecipeCard = ({ recipe }) => {
  const imageUrl = recipe.photos ? recipe.photos : '../assets/images'; // Fallback image
  return (
    <div className="recipe-card">
      <img src={imageUrl} alt={recipe.title} className="recipe-photo" />
      <h2 className="recipe-title">
        <Link to={`/recipes/${recipe.id}`}>{recipe.title}</Link> {/* Link to RecipeDetails */}
      </h2>
      <p>{recipe.description}</p>
    </div>
  );
};

export default RecipeCard;
