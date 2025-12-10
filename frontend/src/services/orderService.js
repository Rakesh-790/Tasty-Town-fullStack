import axiosClient from "../services/axiosClient";

const API_URL = "https://tasty-backend-wvib.onrender.com/tasty-town/api/v1/orders";

export const placeOrder = async (token, data) => {    
    try {
        const response = await axiosClient.post(`${API_URL}`,
            data,
            { 
                headers: { 
                    Authorization: `Bearer ${token}` 
                } 
            }
        );

        // console.log('after placing order', response.data);
        return response.data;
    } catch (error) {
        console.error('Error occured while creating the order', error);
        throw error;
    }
}

export const fetchUserOrders = async (token) => {
    try {
        const response = await axiosClient.get(
            `${API_URL}/user`, 
            {
                headers: { 
                    Authorization: `Bearer ${token}` 
                },
            }
        ); 

        // console.log(`fetched orders of an user by user id`, response.data);
        return response.data;
    } catch (error) {
        console.error('Error occured while fetching the orders', error);
        throw error;
    }
}


// after admin => fetch all orders 
export const fetchAllOrders = async (token) => {   
    try {
        const response = await axiosClient.get(
            `${API_URL}/all`, 
            {
                headers: { 
                    Authorization: `Bearer ${token}` 
                },
            }
        ); 

        // console.log(`fetched all orders for admin`, response.data);
        return response.data;
    } catch (error) {
        console.error('Error occured while fetching the orders', error);
        throw error;
    }
}

export const updateOrderStatus = async (orderCode, newStatus, token) => {
  try {
    const response = await axiosClient.put(
      `${API_URL}/${orderCode}/status`,
      null, // No body, since status is sent as a query param
      {
        params: {
          status: newStatus, // e.g., "DELIVERED"
        },
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );

    // console.log(`Order status updated for ID: ${orderId}`, response.data);
    return response.data;
  } catch (error) {
    console.error('Error while updating order status', error);
    throw error;
  }
};