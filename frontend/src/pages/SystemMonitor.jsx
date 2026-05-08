import { useEffect, useState } from 'react';
import { getMonitorStatus } from '../services/api';

function SystemMonitor() {

  const [monitor, setMonitor] = useState({
    backend: '',
    database: '',
    redis: '',
    docker: ''
  });

  useEffect(() => {

    getMonitorStatus()
        .then((data) => {
          setMonitor(data);
        })
        .catch((error) => {
          console.error(error);
        });

  }, []);

  return (

      <div>

        <h1 className="page-title">
          System Monitor
        </h1>

        <div className="stats-grid">

          <div className="dashboard-panel">

            <h2>Backend</h2>

            <h1>{monitor.backend}</h1>

          </div>

          <div className="dashboard-panel">

            <h2>Database</h2>

            <h1>{monitor.database}</h1>

          </div>

          <div className="dashboard-panel">

            <h2>Redis</h2>

            <h1>{monitor.redis}</h1>

          </div>

          <div className="dashboard-panel">

            <h2>Docker</h2>

            <h1>{monitor.docker}</h1>

          </div>

        </div>

      </div>

  );
}

export default SystemMonitor;