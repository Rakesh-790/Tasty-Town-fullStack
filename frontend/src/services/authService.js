import axiosClient, { setAccessToken } from "../services/axiosClient";

const API = "https://tasty-backend-wvib.onrender.com/tasty-town/api/v1/auth";

export const login = async (credentials) => {
  const response = await axiosClient.post(`${API}/login`, credentials);

  const access = response.data.accessToken;
  const refresh = response.data.refreshToken;

  if (access) {
    localStorage.setItem("accessToken", access);
    setAccessToken(access);
  }

  if (refresh) {
    localStorage.setItem("refreshToken", refresh);
  }

  return response;
};

export const registerUser = async (data) => {
  return axiosClient.post(`${API}/register`, data);
};

export const logout = async () => {
  const token = localStorage.getItem("accessToken");

  return axiosClient.post(
    `${API}/logout`,
    {},
    {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    }
  );
};
