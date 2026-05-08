import { useEffect, useState } from 'react';
import { getPosts, likePost } from '../services/api';

function Posts() {

    const [posts, setPosts] = useState([]);

    const loadPosts = () => {

        getPosts()
            .then((data) => {
                setPosts(data);
            })
            .catch((error) => {
                console.error(error);
            });
    };

    useEffect(() => {
        loadPosts();
    }, []);

    const handleLike = async (id) => {

        try {

            await likePost(id);

            loadPosts();

        } catch (error) {

            console.error(error);
        }
    };

    return (

        <div>

            <h1 className="page-title">
                Posts Feed
            </h1>

            <div className="posts-container">

                {posts.map((post) => (

                    <div
                        className="dashboard-panel"
                        key={post.id}
                    >

                        <h2>{post.title}</h2>

                        <p>{post.content}</p>

                        <br />

                        <div className="post-stats">

              <span>
                👍 Likes: {post.likes}
              </span>

                            <br />

                            <span>
                💬 Comments: {post.comments}
              </span>

                            <br />

                            <span>
                🔥 Virality Score: {post.viralityScore}
              </span>

                            <br />
                            <br />

                            <button
                                className="like-button"
                                onClick={() => handleLike(post.id)}
                            >
                                👍 Like Post
                            </button>

                        </div>

                    </div>

                ))}

            </div>

        </div>

    );
}

export default Posts;