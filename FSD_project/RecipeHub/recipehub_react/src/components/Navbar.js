import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/Navbar.css';
import axios from 'axios';
import {l1} from '../pages/LoginSignup'
const Navbar = () => {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();
  useEffect(() => {
    // Fetch user data
    axios.post('/users')
      .then(response => {
        console.log(response.data)
        setUser(response.data);
      })
      .catch(error => {
        console.error('Error fetching user data:', error);
      });
  }, []);

  

  const handleLogout = () => {
    axios.post('/logout')
      .then(() => {
        setUser(null);
        navigate('/');
      })
      .catch(error => {
        console.error('Error logging out:', error);
      });
  };

  return (
    <nav className="navbar">
      <div className="navbar-container">
        <Link to="/" className="navbar-logo">RecipeHub</Link>
        <ul className="navbar-menu">
          <li className="navbar-item"><Link to="/" className="navbar-link">Home</Link></li>
          <li className="navbar-item"><Link to="/recipes" className="navbar-link">Recipes</Link></li>
          <li className="navbar-item"><Link to="/upload" className="navbar-link">Upload Recipe</Link></li>
          <li className="navbar-item"><Link to="/about" className="navbar-link">About</Link></li>
        </ul>
        <div className="navbar-auth">
          {user ? (
            <>
              <span className="navbar-user">Welcome, {user.username}</span>
              <button onClick={handleLogout} className="navbar-button">Logout</button>
            </>
          ) : (
            <Link to="/login"  className="navbar-button">{l1 ? 'logout' : 'login'}</Link>        
            )}
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
