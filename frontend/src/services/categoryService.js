import axiosClient from "../services/axiosClient";

const BASE_URL = `https://tasty-backend-wvib.onrender.com/tasty-town/api/v1/categories`;

export const fetchCategories = async () => {
    try {
        const response = await axiosClient.get(`${BASE_URL}`);
        // console.log("fetching all categories", response.data);
        return response.data;
    } catch (error) {
        console.error("Error fetching categories", error)   
    }
};

export const deleteCategory = async (categoryId) => {
    const token = localStorage.getItem('accessToken');
    try {
        const response = await axiosClient.delete(`${BASE_URL}/${categoryId}`,
            {
                headers : {
                    Authorization : `Bearer ${token}`
                }
            }
        );       
        console.log(`category deleted`);
        return response; 
    } catch (error) {
        console.error(`Error deleting category`, error);
        throw error;
    }
};

export const addCategory = async (categoryName) => {
    const token = localStorage.getItem('accessToken');
    try {
        const response = await axiosClient.post(`${BASE_URL}/add`,
            {
                categoryName
            },
            {
                headers : {
                    Authorization : `Bearer ${token}`
                }
            }
        );       
        // console.log(`category added `, response.data);
        return response.data; 
    } catch (error) {
        console.error(`Error adding category`, error);
        throw error;
    }
};