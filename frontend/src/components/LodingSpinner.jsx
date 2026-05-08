import { useState } from 'react';
import api from '../services/api';

function CommentSection({ postId, refreshPosts }) {
  const [comment, setComment] = useState('');

  const submitComment = async () => {
    if (!comment.trim()) return;

    try {
      await api.post(`/posts/${postId}/comments`, {
        authorId: 1,
        content: comment,
        depthLevel: 1
      });

      setComment('');
      refreshPosts();
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="comment-section">
      <textarea
        placeholder="Write a comment..."
        value={comment}
        onChange={(e) => setComment(e.target.value)}
      />

      <button onClick={submitComment}>Add Comment</button>
    </div>
  );
}

export default CommentSection;