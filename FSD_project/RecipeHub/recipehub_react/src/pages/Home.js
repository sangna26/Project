import React from 'react';
import '../styles/Home.css';
import { Link } from 'react-router-dom';
import Blog from '../components/Blog';
import UserReview from '../components/UserReview';
const HomePage = () => {
  return (
    <div className="home-page">
      
      <header className="hero-section" >
        <div className="hero-content">
          <h1>Discover Delicious Recipes</h1>
          <p>Find and share your favorite recipes with our community.</p>
          <Link to="/recipes" className="cta-button">Browse Recipes</Link>
        </div>
      </header>      
      <Blog/>
      <section className="special-offers">
        <h2>Special Offers</h2>
        <div className="offer">
          <h3>Exclusive Offer for RecipeHub Members</h3>
          <p>
      Join <strong>RecipeHub</strong> today and enjoy our special limited-time offer! For a short time, get <strong>50% off</strong> on premium membership, giving you access to exclusive recipes, personalized meal plans, and cooking tips from top chefs. Unlock new features like <strong>ad-free browsing</strong>, <strong>priority recipe uploads</strong>, and <strong>early access</strong> to special content.
    </p>
    <p>
      Whether you're a home cook or a culinary enthusiast, this is the perfect opportunity to elevate your cooking game. Don't miss out—sign up now and start your culinary journey with RecipeHub!
    </p>
        </div>
      </section>


      <div className='review-section'>
        <h1>User Review</h1>
          <div className="review">
            
          
          <UserReview 
            review="I’ve tried so many new dishes from RecipeHub, and they never disappoint. The personalized recommendations are spot on, and the platform is super user-friendly!"
            name="Nandita Saffron"
            title="Food Blogger"
            backgroundColor="#e0f7fa"
          />
          <UserReview 
            review="As a professional chef, I appreciate the community-driven nature of RecipeHub. I can share my creations and get feedback from fellow chefs and food enthusiasts."
            name="Sanjeev Kapoor"
            title="Executive Chef"
            backgroundColor="#a5d6a7"
          />
          <UserReview 
            review="RecipeHub has completely transformed the way I cook! The variety of recipes available is amazing, and I love how easy it is to follow along with the instructions."
            name="Poojan Thakkar"
            title="Home Cook"
            backgroundColor="#e0f7fa"
          />
        </div>
      </div>

      
    </div>
  );
};

export default HomePage;
