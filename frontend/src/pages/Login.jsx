import {
    useState
} from 'react';

import {
    useNavigate
} from 'react-router-dom';

function Login() {

    const [username, setUsername] =
        useState('');

    const [error, setError] =
        useState('');

    const [loading, setLoading] =
        useState(false);

    const navigate =
        useNavigate();

    const handleLogin =
        async (e) => {

            e.preventDefault();

            setLoading(true);

            setError('');

            try {

                const response =
                    await fetch(

                        'http://localhost:8083/auth/login',

                        {
                            method: 'POST',

                            headers: {
                                'Content-Type':
                                    'application/json'
                            },

                            body: JSON.stringify({
                                username
                            })
                        }
                    );

                const data =
                    await response.json();

                localStorage.setItem(
                    'token',
                    data.token
                );

                localStorage.setItem(
                    'username',
                    username
                );

                navigate('/');

            } catch (err) {

                console.error(err);

                setError(
                    'Authentication failed'
                );

            } finally {

                setLoading(false);
            }
        };

    return (

        <div
            style={{
                minHeight: '100vh',
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                background:
                    'linear-gradient(135deg, #020617 0%, #031633 45%, #081f4d 100%)',
                overflow: 'hidden',
                position: 'relative',
                padding: '20px'
            }}
        >

            <div
                style={{
                    position: 'absolute',
                    width: '500px',
                    height: '500px',
                    borderRadius: '50%',
                    background:
                        'rgba(59,130,246,0.12)',
                    top: '-120px',
                    right: '-120px',
                    filter: 'blur(30px)'
                }}
            />

            <div
                style={{
                    position: 'absolute',
                    width: '400px',
                    height: '400px',
                    borderRadius: '50%',
                    background:
                        'rgba(96,165,250,0.08)',
                    bottom: '-100px',
                    left: '-100px',
                    filter: 'blur(40px)'
                }}
            />

            <div
                style={{
                    width: '100%',
                    maxWidth: '500px',
                    background:
                        'linear-gradient(145deg, rgba(15,23,42,0.95), rgba(15,23,42,0.75))',
                    border: '1px solid rgba(255,255,255,0.08)',
                    borderRadius: '28px',
                    padding: '50px',
                    backdropFilter: 'blur(18px)',
                    boxShadow:
                        '0 25px 60px rgba(0,0,0,0.45)',
                    position: 'relative',
                    zIndex: 2
                }}
            >

                <div
                    style={{
                        display: 'flex',
                        alignItems: 'center',
                        gap: '14px',
                        marginBottom: '40px'
                    }}
                >

                    <div
                        style={{
                            width: '58px',
                            height: '58px',
                            borderRadius: '18px',
                            background:
                                'linear-gradient(135deg, #3b82f6, #60a5fa)',
                            display: 'flex',
                            alignItems: 'center',
                            justifyContent: 'center',
                            fontSize: '28px',
                            fontWeight: 'bold',
                            color: 'white',
                            boxShadow:
                                '0 10px 30px rgba(59,130,246,0.45)'
                        }}
                    >
                        a
                    </div>

                    <div>

                        <h1
                            style={{
                                color: 'white',
                                margin: 0,
                                fontSize: '34px',
                                fontWeight: '800'
                            }}
                        >
                            aICE
                        </h1>

                        <p
                            style={{
                                margin: '4px 0 0 0',
                                color: '#94a3b8',
                                fontSize: '14px'
                            }}
                        >
                            AI Interaction Control Engine
                        </p>

                    </div>

                </div>

                <div
                    style={{
                        marginBottom: '35px'
                    }}
                >

                    <h2
                        style={{
                            color: 'white',
                            fontSize: '30px',
                            marginBottom: '10px'
                        }}
                    >
                        Secure Access Portal
                    </h2>

                    <p
                        style={{
                            color: '#94a3b8',
                            lineHeight: '1.7'
                        }}
                    >
                        Authenticate to access the real-time analytics,
                        Redis protection layer, WebSocket monitoring,
                        and AI interaction management system.
                    </p>

                </div>

                <form
                    onSubmit={handleLogin}
                >

                    <div
                        style={{
                            marginBottom: '24px'
                        }}
                    >

                        <label
                            style={{
                                display: 'block',
                                color: '#cbd5e1',
                                marginBottom: '10px',
                                fontSize: '14px',
                                fontWeight: '600'
                            }}
                        >
                            Username
                        </label>

                        <input
                            type="text"
                            placeholder="Enter your username"
                            value={username}
                            onChange={(e) =>
                                setUsername(
                                    e.target.value
                                )
                            }
                            required
                            style={{
                                width: '100%',
                                padding: '16px 18px',
                                borderRadius: '16px',
                                border:
                                    '1px solid rgba(255,255,255,0.08)',
                                background:
                                    'rgba(15,23,42,0.75)',
                                color: 'white',
                                outline: 'none',
                                fontSize: '15px',
                                boxSizing: 'border-box'
                            }}
                        />

                    </div>

                    <button
                        type="submit"
                        disabled={loading}
                        style={{
                            width: '100%',
                            padding: '16px',
                            border: 'none',
                            borderRadius: '16px',
                            background:
                                'linear-gradient(135deg, #2563eb, #60a5fa)',
                            color: 'white',
                            fontSize: '16px',
                            fontWeight: '700',
                            cursor: 'pointer',
                            transition: '0.3s ease',
                            boxShadow:
                                '0 12px 35px rgba(37,99,235,0.35)'
                        }}
                    >

                        {
                            loading
                                ? 'Authenticating...'
                                : 'Access Dashboard'
                        }

                    </button>

                    {
                        error && (

                            <div
                                style={{
                                    marginTop: '20px',
                                    padding: '14px',
                                    borderRadius: '14px',
                                    background:
                                        'rgba(239,68,68,0.12)',
                                    border:
                                        '1px solid rgba(239,68,68,0.25)',
                                    color: '#fca5a5',
                                    fontSize: '14px'
                                }}
                            >
                                {error}
                            </div>
                        )
                    }

                </form>

                <div
                    style={{
                        marginTop: '35px',
                        display: 'flex',
                        justifyContent: 'space-between',
                        gap: '12px',
                        flexWrap: 'wrap'
                    }}
                >

                    {
                        [
                            'JWT Security',
                            'Redis Protection',
                            'Live Analytics'
                        ].map((item) => (

                            <div
                                key={item}
                                style={{
                                    padding: '10px 14px',
                                    borderRadius: '14px',
                                    background:
                                        'rgba(59,130,246,0.1)',
                                    border:
                                        '1px solid rgba(96,165,250,0.12)',
                                    color: '#93c5fd',
                                    fontSize: '13px',
                                    fontWeight: '600'
                                }}
                            >
                                {item}
                            </div>
                        ))
                    }

                </div>

            </div>

        </div>
    );
}

export default Login;