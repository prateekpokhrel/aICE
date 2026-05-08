import { useEffect, useState } from 'react';
import { getNotifications } from '../services/api';

function Notifications() {

    const [notifications, setNotifications] =
        useState([]);

    const [loading, setLoading] =
        useState(true);

    const [error, setError] =
        useState('');

    useEffect(() => {

        getNotifications()

            .then((data) => {

                if (Array.isArray(data)) {

                    setNotifications(data);

                } else {

                    setNotifications([]);

                    setError(
                        'Invalid notification response'
                    );
                }

                setLoading(false);
            })

            .catch((error) => {

                console.error(error);

                setNotifications([]);

                setError(
                    'Failed to load notifications'
                );

                setLoading(false);
            });

    }, []);

    if (loading) {

        return (

            <div>

                <h1 className="page-title">
                    Notifications
                </h1>

                <div className="dashboard-panel">

                    <h3>
                        Loading notifications...
                    </h3>

                </div>

            </div>
        );
    }

    return (

        <div>

            <h1 className="page-title">
                Notifications
            </h1>

            {

                error && (

                    <div className="dashboard-panel">

                        <h3>
                            {error}
                        </h3>

                    </div>
                )
            }

            {

                notifications.length === 0 && !error && (

                    <div className="dashboard-panel">

                        <h3>
                            No notifications found
                        </h3>

                    </div>
                )
            }

            {

                notifications.map((notification) => (

                    <div
                        className="dashboard-panel"
                        key={notification.id}
                    >

                        <h3>
                            {notification.message}
                        </h3>

                    </div>

                ))
            }

        </div>
    );
}

export default Notifications;