import React from 'react';
import '../styles/Footer.css';

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-section">
        <h3>About Us</h3>
        <p>RecipeHub: Discover, share, and explore recipes from a global community of food lovers.</p>
        <a href='/about'>About Us</a>
      </div>
      <div className="footer-section">
        <h3>Contact Info</h3>
        <p>Address: Your address goes here, your demo address.</p>
        <p>Phone: +8880 44338899</p>
        <p>Email: info@domain.com</p>
      </div>
      <div className="footer-section">
        <h3>Important Link</h3>
        <p></p>
        <ul>
          <li><a href="/">Recipe Home</a></li>
          <li><a href="/recipe">Recipe View</a></li>
          <li><a href="/upload">Recipe Upload</a></li>
        </ul>
      </div>
      <div className="footer-section">
        <h2 style={{color:'#F2E8C6'}}>Stay Updated</h2>
        <p>Subscribe to our newsletter for the latest recipes, tips, and special offers.</p>
        <form>
          <input type="email" placeholder="Enter your email" required />
          <button type="submit" className="subscribe-button">Subscribe</button>
        </form>
      </div>
      <div className="footer-social">
        <a href="#"><i className="fab fa-facebook-f"></i></a>
        <a href="#"><i className="fab fa-twitter"></i></a>
        <a href="#"><i className="fab fa-instagram"></i></a>
      </div>
      <div className="footer-bottom">
        <p>© 2024 All rights reserved | This Website Made by Sangna Limbasiya</p>
      </div>
    </footer>
  );
};

export default Footer;
