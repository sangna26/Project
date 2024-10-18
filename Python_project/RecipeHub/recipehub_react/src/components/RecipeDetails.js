import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import '../styles/RecipeDetails.css';

const RecipeDetails = () => {
  const { recipeId } = useParams();
  console.log(recipeId) // Get recipeId from URL
  const [recipe, setRecipe] = useState(null);
  const [error, setError] = useState(null);  // Add error state

  useEffect(() => {
    const fetchRecipeDetails = async () => {
      try {
        const response = await axios.get(`http://localhost:8000/api/recipes/${recipeId}/`);
        setRecipe(response.data);
      } catch (error) {
        console.error('Error fetching recipe details:', error);
        setError('Failed to load recipe details.');  // Set error message
      }
    };

    if (recipeId) {
      fetchRecipeDetails();
    } else {
      setError('Recipe ID is not defined.');
    }
  }, [recipeId]);

  if (error) {
    return <div>{error}</div>;  // Display error message
  }

  if (!recipe) {
    return <div>Loading...</div>;
  }

  return (
    <div className="recipe-details">
      <table>
        <tbody>
          <tr>
            <td className="details-column">
              <h1>{recipe.title}</h1>
              <p>{recipe.description}</p>

              <h2>Ingredients</h2>
              <ul>
                {recipe.ingredients.split('\n').map((ingredient, index) => (
                  <li key={index}>{ingredient}</li>
                ))}
              </ul>

              <h2>Instructions</h2>
              <ol>
                {recipe.instructions.split('\n').map((instruction, index) => (
                  <li key={index}>{instruction}</li>
                ))}
              </ol>

              {/* <div className="user-info">
                <h3>Uploaded by: {recipe.username}</h3>
                <p>Email: {recipe.email}</p>
              </div> */}
            </td>
            <td className="photo-column">
              {recipe.photos ? (
                <img src={recipe.photos} alt={recipe.title} />
              ) : (
                <p>No photo available</p>
              )}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default RecipeDetails;
