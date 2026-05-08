import {
    useEffect,
    useState
} from 'react';

import {
    LineChart,
    Line,
    XAxis,
    YAxis,
    Tooltip,
    CartesianGrid,
    ResponsiveContainer,
    BarChart,
    Bar
} from 'recharts';

import {
    getDashboardStats,
    getPosts
} from '../services/api';

import {
    connectWebSocket,
    disconnectWebSocket
} from '../services/WebSocketService';

function Analytics() {

    const [stats, setStats] = useState({
        totalUsers: 0,
        totalPosts: 0,
        totalComments: 0,
        topViralityScore: 0
    });

    const [chartData, setChartData] =
        useState([]);

    const loadAnalyticsData =
        async () => {

            try {

                const statsData =
                    await getDashboardStats();

                const postsData =
                    await getPosts();

                setStats(statsData);

                const formattedData =
                    postsData.map((post) => ({

                        name:
                        post.title,

                        likes:
                        post.likes,

                        comments:
                        post.comments,

                        virality:
                        post.viralityScore
                    }));

                setChartData(
                    formattedData
                );

            } catch (error) {

                console.error(error);
            }
        };

    useEffect(() => {

        loadAnalyticsData();

        connectWebSocket(

            () => {

                loadAnalyticsData();
            },

            () => {

                loadAnalyticsData();
            }
        );

        return () => {

            disconnectWebSocket();
        };

    }, []);

    return (

        <div>

            <h1 className="page-title">
                Real-Time Analytics
            </h1>

            <div className="stats-grid">

                <div className="dashboard-panel">

                    <h2>Total Users</h2>

                    <h1>
                        {stats.totalUsers}
                    </h1>

                </div>

                <div className="dashboard-panel">

                    <h2>Total Posts</h2>

                    <h1>
                        {stats.totalPosts}
                    </h1>

                </div>

                <div className="dashboard-panel">

                    <h2>Total Comments</h2>

                    <h1>
                        {stats.totalComments}
                    </h1>

                </div>

                <div className="dashboard-panel">

                    <h2>Top Virality</h2>

                    <h1>
                        {stats.topViralityScore}
                    </h1>

                </div>

            </div>

            <div className="dashboard-panel">

                <h2>
                    Virality Trend Chart
                </h2>

                <ResponsiveContainer
                    width="100%"
                    height={400}
                >

                    <LineChart
                        data={chartData}
                    >

                        <CartesianGrid
                            strokeDasharray="3 3"
                        />

                        <XAxis dataKey="name" />

                        <YAxis />

                        <Tooltip />

                        <Line
                            type="monotone"
                            dataKey="virality"
                            stroke="#4dabf7"
                            strokeWidth={3}
                        />

                    </LineChart>

                </ResponsiveContainer>

            </div>

            <div className="dashboard-panel">

                <h2>
                    Likes vs Comments
                </h2>

                <ResponsiveContainer
                    width="100%"
                    height={400}
                >

                    <BarChart
                        data={chartData}
                    >

                        <CartesianGrid
                            strokeDasharray="3 3"
                        />

                        <XAxis dataKey="name" />

                        <YAxis />

                        <Tooltip />

                        <Bar
                            dataKey="likes"
                            fill="#4dabf7"
                        />

                        <Bar
                            dataKey="comments"
                            fill="#82ca9d"
                        />

                    </BarChart>

                </ResponsiveContainer>

            </div>

        </div>

    );
}

export default Analytics;