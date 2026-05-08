const BASE_URL = 'http://localhost:8083';

function getAuthHeaders() {

  const token =
      localStorage.getItem(
          'token'
      );

  return {

    'Content-Type':
        'application/json',

    Authorization:
        `Bearer ${token}`
  };
}

export async function getDashboardStats() {

  const response = await fetch(

      `${BASE_URL}/analytics/dashboard`,

      {
        headers:
            getAuthHeaders()
      }
  );

  return response.json();
}

export async function getPosts() {

  const response = await fetch(

      `${BASE_URL}/posts`,

      {
        headers:
            getAuthHeaders()
      }
  );

  return response.json();
}

export async function likePost(id) {

  const response = await fetch(

      `${BASE_URL}/posts/${id}/like`,

      {
        method: 'PUT',

        headers:
            getAuthHeaders()
      }
  );

  return response.json();
}

export async function getNotifications() {

  const response = await fetch(

      `${BASE_URL}/notifications`,

      {
        headers:
            getAuthHeaders()
      }
  );

  return response.json();
}

export async function getMonitorStatus() {

  const response = await fetch(

      `${BASE_URL}/monitor`,

      {
        headers:
            getAuthHeaders()
      }
  );

  return response.json();
}