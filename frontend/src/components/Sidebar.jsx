import { Link, useLocation } from 'react-router-dom';

function Sidebar() {

  const location = useLocation();

  return (

    <aside className="sidebar">

      {/* TOP */}

      <div className="sidebar-top">

        <h1 className="logo">
          aICE
        </h1>

      </div>

      {/* NAVIGATION */}

      <nav className="sidebar-nav">

        <Link
          to="/"
          className={
            location.pathname === '/'
              ? 'active-link'
              : ''
          }
        >

          <span className="nav-text">
            Dashboard
          </span>

        </Link>

        <Link
          to="/posts"
          className={
            location.pathname === '/posts'
              ? 'active-link'
              : ''
          }
        >

          <span className="nav-text">
            Posts
          </span>

        </Link>

        <Link
          to="/analytics"
          className={
            location.pathname === '/analytics'
              ? 'active-link'
              : ''
          }
        >
          <span className="nav-text">
            Analytics
          </span>

        </Link>

        <Link
          to="/notifications"
          className={
            location.pathname === '/notifications'
              ? 'active-link'
              : ''
          }
        >

          <span className="nav-text">
            Notifications
          </span>

        </Link>

        <Link
          to="/monitor"
          className={
            location.pathname === '/monitor'
              ? 'active-link'
              : ''
          }
        >
        <span className="nav-text">
            System Monitor
          </span>

        </Link>

      </nav>

      {/* FOOTER */}

      <div className="sidebar-footer">

        <p>
          aICE v1.0
        </p>

      </div>

    </aside>

  );
}

export default Sidebar;