import {
    useNavigate
} from 'react-router-dom';

function Navbar() {

    const navigate =
        useNavigate();

    const username =
        localStorage.getItem(
            'username'
        );

    const handleLogout = () => {

        localStorage.clear();

        navigate('/login');
    };

    return (

        <div className="navbar">

            <h1>
                aICE Dashboard
            </h1>

            <div
                style={{
                    display: 'flex',
                    alignItems: 'center',
                    gap: '16px'
                }}
            >

                <div
                    style={{
                        display: 'flex',
                        alignItems: 'center',
                        gap: '10px',
                        background:
                            'rgba(255,255,255,0.04)',
                        padding: '10px 16px',
                        borderRadius: '14px',
                        border:
                            '1px solid rgba(255,255,255,0.06)'
                    }}
                >

                    <div
                        style={{
                            width: '10px',
                            height: '10px',
                            borderRadius: '50%',
                            background: '#22c55e'
                        }}
                    />

                    <span
                        style={{
                            color: '#e2e8f0',
                            fontWeight: '600'
                        }}
                    >
                        {username}
                    </span>

                </div>

                <button

                    onClick={handleLogout}

                    style={{
                        border: 'none',
                        background:
                            'linear-gradient(135deg,#ef4444,#f97316)',
                        color: 'white',
                        padding: '10px 18px',
                        borderRadius: '14px',
                        fontWeight: '700',
                        cursor: 'pointer',
                        boxShadow:
                            '0 8px 24px rgba(239,68,68,0.25)'
                    }}
                >

                    Logout

                </button>

            </div>

        </div>
    );
}

export default Navbar;