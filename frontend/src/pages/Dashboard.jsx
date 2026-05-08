import { useEffect, useState } from 'react';
import StatsCard from '../components/StatsCard';
import { getDashboardStats } from '../services/api';

import {
    connectWebSocket,
    disconnectWebSocket
} from '../services/WebSocketService';

function Dashboard() {

    const [stats, setStats] = useState({
        totalUsers: 0,
        totalPosts: 0,
        totalComments: 0,
        topViralityScore: 0,
        backendStatus: 'OFFLINE'
    });

    const [liveNotification, setLiveNotification] =
        useState('');

    useEffect(() => {

        getDashboardStats()
            .then((data) => {

                setStats({
                    totalUsers: data.totalUsers,
                    totalPosts: data.totalPosts,
                    totalComments: data.totalComments,
                    topViralityScore:
                    data.topViralityScore,
                    backendStatus: 'ONLINE'
                });

            })
            .catch((error) => {

                console.error(error);

                setStats((prev) => ({
                    ...prev,
                    backendStatus: 'OFFLINE'
                }));
            });

        connectWebSocket(

            (notification) => {

                setLiveNotification(
                    notification
                );

                console.log(
                    'Live Notification:',
                    notification
                );
            },

            (analyticsUpdate) => {

                console.log(
                    'Live Analytics Update:',
                    analyticsUpdate
                );

                getDashboardStats()
                    .then((data) => {

                        setStats({
                            totalUsers:
                            data.totalUsers,

                            totalPosts:
                            data.totalPosts,

                            totalComments:
                            data.totalComments,

                            topViralityScore:
                            data.topViralityScore,

                            backendStatus:
                                'ONLINE'
                        });
                    });
            }
        );

        return () => {

            disconnectWebSocket();
        };

    }, []);

    return (

        <div>

            <h1 className="page-title">
                AI Interaction Control Engine
            </h1>

            {

                liveNotification && (

                    <div className="dashboard-panel">

                        <h2>
                            Live Notification
                        </h2>

                        <p>
                            {liveNotification}
                        </p>

                    </div>
                )
            }

            <div className="stats-grid">

                <StatsCard
                    title="Total Users"
                    value={stats.totalUsers}
                />

                <StatsCard
                    title="Total Posts"
                    value={stats.totalPosts}
                />

                <StatsCard
                    title="Total Comments"
                    value={stats.totalComments}
                />

                <StatsCard
                    title="Top Virality"
                    value={stats.topViralityScore}
                />

                <StatsCard
                    title="Backend Status"
                    value={stats.backendStatus}
                />

            </div>

            <div className="dashboard-panel">

                <h2>System Overview</h2>

                <p>
                    aICE monitors AI interactions,
                    virality scoring,
                    Redis cooldown protection,
                    PostgreSQL persistence,
                    notification throttling,
                    and real-time analytics.
                </p>

            </div>

        </div>
    );
}

export default Dashboard;