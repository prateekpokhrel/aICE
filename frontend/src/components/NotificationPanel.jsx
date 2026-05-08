function NotificationPanel({ notifications }) {
  return (
    <div className="dashboard-panel">
      <h2>Recent Notifications</h2>

      {notifications.length === 0 ? (
        <p>No notifications available.</p>
      ) : (
        notifications.map((notif, index) => (
          <div key={index} className="notification-item">
            {notif}
          </div>
        ))
      )}
    </div>
  );
}

export default NotificationPanel;