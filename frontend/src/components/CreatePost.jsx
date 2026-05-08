import { useState } from 'react';
import api from '../services/api';

function CreatePost({ refreshPosts }) {

  const [content, setContent] = useState('');

  const handleSubmit = async () => {

    if (!content.trim()) return;

    try {

      await api.post('/posts', {
        authorId: 1,
        content
      });

      setContent('');
      refreshPosts();

    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="create-post-card">

      <textarea
        placeholder="Create a new post..."
        value={content}
        onChange={(e) => setContent(e.target.value)}
      />

      <button onClick={handleSubmit}>
        Publish Post
      </button>

    </div>
  );
}

export default CreatePost;