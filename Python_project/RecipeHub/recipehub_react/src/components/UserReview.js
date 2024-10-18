import React from 'react';
import '../styles/UserReview.css';

const UserReview = ({ review, name, title, backgroundColor }) => {
  return (
    <div className="review-card" style={{ backgroundColor }}>
      <div className="quote-mark">“</div>
      <p className="review-text">{review}</p>
      <div className="reviewer-info">
        <div className="reviewer-name">{name}</div>
        <div className="reviewer-title">{title}</div>
      </div>
    </div>
  );
};

export default UserReview;
