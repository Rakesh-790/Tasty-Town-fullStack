import axios from "axios"

const BASE_URL = `http://localhost:1300/tasty-town/api/v1/catagories`;

export const fetchCategories = async () => {
    try {
        const response = await axios.get(`${BASE_URL}`);
        // console.log("fetching all categories", response.data);
        return response.data;
    } catch (error) {
        console.error("Error fetching categories", error)   
    }
};

export const deleteCategory = async (catagoryId) => {
    const token = localStorage.getItem('accessToken');
    try {
        const response = await axios.delete(`${BASE_URL}/${catagoryId}`,
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

export const addCategory = async (catagoryName) => {
    const token = localStorage.getItem('accessToken');
    try {
        const response = await axios.post(`${BASE_URL}/add`,
            {
                catagoryName
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