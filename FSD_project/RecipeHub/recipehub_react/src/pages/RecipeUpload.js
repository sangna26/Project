import React, { useState } from 'react';
import axios from 'axios';
import '../styles/RecipeUpload.css';
import { useParams } from "react-router-dom";

 
const RecipeUpload = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [ingredients, setIngredients] = useState('');
  const [instructions, setInstructions] = useState('');
  const [videoLink, setVideoLink] = useState('');
  const [photos, setPhotos] = useState(null);
  const [category, setCategory] = useState('');
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);

  

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append('title', title);
    formData.append('description', description);
    formData.append('ingredients', ingredients);
    formData.append('instructions', instructions);
    formData.append('video_link', videoLink);
    if (photos) {
      for (let i = 0; i < photos.length; i++) {
        formData.append('photos', photos[i]);
      }
    }
    formData.append('category', category);

    try {
      const response = await axios.post('http://localhost:8000/api/recipes/', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      setSuccess('Recipe uploaded successfully!');
      setError(null);
      // Reset form fields
      setTitle('');
      setDescription('');
      setIngredients('');
      setInstructions('');
      setVideoLink('');
      setPhotos(null);
      setCategory('');
    } catch (error) {
      console.error('Error uploading recipe:', error.response ? error.response.data : error.message);
      setError('Error uploading recipe. Please try again.');
      setSuccess(null);
    }
  };

  const handleChange = (e) => {
    const { name, value, files } = e.target;
    if (name === 'photos') {
      setPhotos(files);
    } else {
      switch (name) {
        case 'title':
          setTitle(value);
          break;
        case 'description':
          setDescription(value);
          break;
        case 'ingredients':
          setIngredients(value);
          break;
        case 'instructions':
          setInstructions(value);
          break;
        case 'video_link':
          setVideoLink(value);
          break;
        case 'category':
          setCategory(value);
          break;
        default:
          break;
      }
    }
  };

  return (
    <div className='upload-div'>
      <form className="recipe-upload-form" onSubmit={handleSubmit}>
        <label className='formtitle'>  Upload Recipe....</label>
        <table>
          <tbody>
            <tr>
              <td><label htmlFor="title">Title</label></td>
              <td>
                <input
                  className='inputfield'
                  type="text"
                  id="title"
                  name="title"
                  value={title}
                  onChange={handleChange}
                  required
                />
              </td>
            </tr>
            <tr>
              <td><label htmlFor="description">Description</label></td>
              <td>
                <textarea
                  className='inputfield'
                  id="description"
                  name="description"
                  value={description}
                  onChange={handleChange}
                  required
                />
              </td>
            </tr>
            <tr>
              <td><label htmlFor="ingredients">Ingredients</label></td>
              <td>
                <textarea
                  className='inputfield'
                  id="ingredients"
                  name="ingredients"
                  value={ingredients}
                  onChange={handleChange}
                  required
                />
              </td>
            </tr>
            <tr>
              <td><label htmlFor="instructions">Instructions</label></td>
              <td>
                <textarea
                  className='inputfield'
                  id="instructions"
                  name="instructions"
                  value={instructions}
                  onChange={handleChange}
                  required
                />
              </td>
            </tr>
            <tr>
              <td><label htmlFor="video_link">Video Link</label></td>
              <td>
                <input
                  className='inputfield'
                  type="url"
                  id="video_link"
                  name="video_link"
                  value={videoLink}
                  onChange={handleChange}
                />
              </td>
            </tr>
            <tr>
              <td><label htmlFor="photos">Photos</label></td>
              <td>
                <input
                  className='inputfield'
                  type="file"
                  id="photos"
                  name="photos"
                  multiple
                  onChange={handleChange}
                />
              </td>
            </tr>
            <tr>
              <td><label htmlFor="category">Category</label></td>
              <td>
                <select
                  className='inputfield'
                  id="category"
                  name="category"
                  value={category}
                  onChange={handleChange}
                  required
                >
                  <option value="" disabled>Select a category</option>
                  <option value="Mexican">Mexican</option>
                  <option value="Italian Cuisine">Italian Cuisine</option>
                  <option value="Chinese">Chinese</option>
                  <option value="Indian Food">Indian Food</option>
                  <option value="Other">Other</option>
                </select>
              </td>
            </tr>
          </tbody>
        </table>
        <button type="submit" className="upload-button">Upload Recipe</button> 
        {error && <p className="error-message">{error}</p>}
      </form>
    </div>
  );
};

export default RecipeUpload;
