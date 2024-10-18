import React, { useEffect, useRef } from 'react';
import '../styles/Blog.css'; // Import the CSS file
import Blog1 from '../assets/images/Blog1.png' ;
import Blog2 from '../assets/images/Blog2.png';
import Blog3 from '../assets/images/Blog3.png';
const Blog = () => {
  const blogRefs = [useRef(null), useRef(null), useRef(null)];

  useEffect(() => {
    const options = {
      threshold: 0.1,
    };

    const observer = new IntersectionObserver((entries) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          entry.target.classList.add('visible');
        }
      });
    }, options);

    blogRefs.forEach((ref) => {
      if (ref.current) {
        observer.observe(ref.current);
      }
    });

    return () => {
      blogRefs.forEach((ref) => {
        if (ref.current) {
          observer.unobserve(ref.current);
        }
      });
    };
  }, [blogRefs]);

  return (
    <div className="blogs-container">
      <div className="blog-container blog-left" ref={blogRefs[0]}>
        <div className="blog-content">
          <h2>Discover Unique Recipes on RecipeHub</h2>
          <p>
          RecipeHub is your go-to platform for finding new and exciting recipes from around the world. Whether you’re in the mood for comfort food or gourmet cuisine, our community has something for every palate. Explore now and get inspired to create something amazing in your kitchen!
          </p>
        </div>
        <div className="blog-image">
          <img src={Blog3} alt="WealthPay" />
        </div>
      </div>

      <div className="blog-container blog-right" ref={blogRefs[1]}>
        <div className="blog-content">
          <h2>Elevate Your Cooking with RecipeHub</h2>
          <p>
          Looking to enhance your culinary skills? RecipeHub provides a treasure trove of recipes that cater to all skill levels. Whether you’re a novice cook or a seasoned chef, our diverse recipe collection will inspire you to try new techniques and ingredients. Discover your next great meal today!
          </p>
        </div>
        <div className="blog-image">
          <img src={Blog2} alt="WealthPay" />
        </div>
      </div>

      <div className="blog-container blog-left" ref={blogRefs[2]}>
        <div className="blog-content">
          <h2>Share Your Culinary Creations on RecipeHub</h2>
          <p>
          Have a family recipe you’re proud of or a new dish you’ve just perfected? Share it on RecipeHub! Our platform allows home cooks and professional chefs to upload recipes and showcase their skills to a community of passionate food lovers. Start sharing today!          </p>
        </div>
        <div className="blog-image">
          <img src={Blog1} alt="WealthPay" />
        </div>
      </div>
    </div>
  );
};

export default Blog;
