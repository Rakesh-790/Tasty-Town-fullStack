import axios from "axios";

let accessToken = localStorage.getItem("accessToken") || null;

export function setAccessToken(token) {
  accessToken = token;
}

// Create instance
const axiosClient = axios.create({
  baseURL: "https://tasty-backend-wvib.onrender.com/tasty-town",
});

// Attach token
axiosClient.interceptors.request.use((config) => {
  if (accessToken) {
    config.headers.Authorization = `Bearer ${accessToken}`;
  }
  return config;
});

// Refresh token function
async function refreshAccessToken() {
  const refreshToken = localStorage.getItem("refreshToken");
  if (!refreshToken) throw new Error("No refresh token");

  const { data } = await axios.post(
    "https://tasty-backend-wvib.onrender.com/tasty-town/api/v1/auth/refresh",
    { refreshToken }
  );

  accessToken = data.accessToken;
  localStorage.setItem("accessToken", data.accessToken);

  return data.accessToken;
}

// Response interceptor
axiosClient.interceptors.response.use(
  (res) => res,
  async (error) => {
    const originalRequest = error.config;

    // Do not refresh token for public auth routes
    if (
      originalRequest.url.includes("/auth/login") ||
      originalRequest.url.includes("/auth/register") ||
      originalRequest.url.includes("/auth/refresh")
    ) {
      return Promise.reject(error);
    }

    // Handle 401 for protected routes
    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;

      try {
        const newToken = await refreshAccessToken();
        originalRequest.headers.Authorization = `Bearer ${newToken}`;
        return axiosClient(originalRequest);
      } catch (err) {
        console.log("Session expired. Please login again.");
        return Promise.reject(err);
      }
    }

    return Promise.reject(error);
  }
);

export default axiosClient;
