import React, { useState, createContext, useContext } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navbar from './components/Navbar';
import Footer from './components/Footer';
import Home from './pages/Home';
import Recipes from './pages/Recipes';
import RecipeUpload from './pages/RecipeUpload';
import About from './pages/About';
import LoginSignup from './pages/LoginSignup';
import RecipeDetails from './components/RecipeDetails';

// Create a User Context
const UserContext = createContext();
const UserActionsContext = createContext();

const App = () => {
  const [user, setUser] = useState(null); // Manage user state

  const login = (userData) => {
    setUser(userData); // Set user data on login
  };

  const logout = () => {
    setUser(null); // Clear user data on logout
  };

  return (
    <UserContext.Provider value={{ user }}>
      <UserActionsContext.Provider value={{ login, logout }}>
        <Router>
          <Navbar />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/recipes" element={<Recipes />} />
            <Route path="/recipes/:recipeId" element={<RecipeDetails />} />
            <Route path="/upload" element={<RecipeUpload />} />
            <Route path="/about" element={<About />} />
            <Route path="/login" element={<LoginSignup />} />
          </Routes>
          <Footer />
        </Router>
      </UserActionsContext.Provider>
    </UserContext.Provider>
  );
};

// Custom hook to use the User Context
export const useUser = () => useContext(UserContext);
export const useUserActions = () => useContext(UserActionsContext);

export default App;
