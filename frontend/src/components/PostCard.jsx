import api from '../services/api';

function PostCard({ post, refreshPosts }) {
  const likePost = async () => {
    await api.post(`/posts/${post.id}/like`);
    refreshPosts();
  };

  return (
    <div className="post-card">
      <div className="post-header">
        <h3>{post.authorName}</h3>
        <span>{post.createdAt}</span>
      </div>

      <p>{post.content}</p>

      <div className="post-footer">
        <button onClick={likePost}>Like</button>

        <div className="virality-box">
          Virality: {post.viralityScore}
        </div>
      </div>
    </div>
  );
}

export default PostCard;