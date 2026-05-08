import { BrowserRouter, Routes, Route } from 'react-router-dom';

import Dashboard from './pages/Dashboard';
import Posts from './pages/Posts';
import Analytics from './pages/Analytics';
import Notifications from './pages/Notifications';
import SystemMonitor from './pages/SystemMonitor';
import Login from './pages/Login';

import Navbar from './components/Navbar';
import Sidebar from './components/Sidebar';
import ProtectedRoute from './components/ProtectedRoute';

function App() {

  return (

      <BrowserRouter>

        <Routes>

          <Route
              path="/login"
              element={<Login />}
          />

          <Route
              path="/*"
              element={

                <ProtectedRoute>

                  <div className="app-layout">

                    <Sidebar />

                    <div className="main-area">

                      <Navbar />

                      <div className="content-area">

                        <Routes>

                          <Route
                              path="/"
                              element={<Dashboard />}
                          />

                          <Route
                              path="/posts"
                              element={<Posts />}
                          />

                          <Route
                              path="/analytics"
                              element={<Analytics />}
                          />

                          <Route
                              path="/notifications"
                              element={<Notifications />}
                          />

                          <Route
                              path="/monitor"
                              element={<SystemMonitor />}
                          />

                        </Routes>

                      </div>

                    </div>

                  </div>

                </ProtectedRoute>
              }
          />

        </Routes>

      </BrowserRouter>

  );
}

export default App;