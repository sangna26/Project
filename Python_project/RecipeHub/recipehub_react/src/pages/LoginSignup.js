import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../styles/LoginSignup.css';
import Navbar from '../components/Navbar';

var l1 = false
const LoginSignup = () => {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [profilePhoto, setProfilePhoto] = useState(null);
  const [isLogin, setIsLogin] = useState(true);
  const [error, setError] = useState('');
  const navigate = useNavigate();
  
  

  // Function to get CSRF token from cookies
  const getCSRFToken = () => {
    const token = document.cookie.match(/csrftoken=([\w-]+)/);
    return token ? token[1] : '';
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const url = isLogin ? 'http://localhost:8000/users/login/' : 'http://localhost:8000/users/signup/';
    const data = new FormData();

    if (isLogin) {
      data.append('username', username);
      data.append('password', password);
    } else {
      if (password !== confirmPassword) {
        setError('Passwords do not match');
        return;
      }
      data.append('username', username);
      data.append('email', email);
      data.append('password', password);
      data.append('confirm_password', confirmPassword);
      if (profilePhoto) data.append('profile_photo', profilePhoto);
    }

    try {
      console.log(data)
      const response = await axios.post(url, data, {
        headers: {
          'Content-Type': 'multipart/form-data',
          'X-CSRFToken': getCSRFToken(),
        },
      });

      if (response.status === 200 || response.status === 201) {
        l1=true
        navigate('/'); // Redirect after successful login/signup
      }
    } catch (error) {
      console.error('Error:', error.response ? error.response.data : 'An error occurred');
      setError(
        error.response
          ? error.response.data.error || 'An error occurred'
          : 'An error occurred'
      );
    }
  };
  

  return (
    <div className="login-signup">
      <h1>{isLogin ? 'Login' : 'Sign Up'}</h1>
      <form onSubmit={handleSubmit}>
        {!isLogin && (
          <div className="form-group">
            <label htmlFor="username">Username</label>
            <input
              type="text"
              id="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
        )}
        {isLogin && (
          <div className="form-group">
            <label htmlFor="username">Username </label>
            <input
              type="text"
              id="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
        )}
        {!isLogin && (
          <div className="form-group">
            <label htmlFor="email">Email</label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
        )}
        <div className="form-group">
          <label htmlFor="password">{isLogin ? 'Password' : 'Create Password'}</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        {!isLogin && (
          <div className="form-group">
            <label htmlFor="confirm_password">Confirm Password</label>
            <input
              type="password"
              id="confirm_password"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              required
            />
          </div>
        )}
        {!isLogin && (
          <div className="form-group">
            <label htmlFor="profile_photo">Profile Photo</label>
            <input
              type="file"
              id="profile_photo"
              accept="image/*"
              onChange={(e) => setProfilePhoto(e.target.files[0])}
            />
          </div>
        )}
        <button type="submit" className="submit-button">
          {isLogin ? 'Login' : 'Sign Up'}
        </button>
        {error && <p className="error-message">{error}</p>}
      </form>
      <button onClick={() => setIsLogin(!isLogin)} className="toggle-button">
        {isLogin ? 'Switch to Sign Up' : 'Switch to Login'}
      </button>
    </div>
  );
};
export {l1}
export default LoginSignup;