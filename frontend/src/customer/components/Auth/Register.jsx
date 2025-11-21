import React, { useState } from "react";
import "./Register.css";
import { data, Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { registerUser } from "../../../services/authService";
import { setAccessToken } from "../../../services/axiosClient";

const Register = () => {
  const navigate = useNavigate();
  const [data, setData] = useState({
    userEmail: "",
    userPassword: "",
  });
  const onChangeHandler = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setData((data) => ({ ...data, [name]: value }));
  };

  const onSubmitHandler = async (event) => {
    event.preventDefault();

    localStorage.removeItem("accessToken");
    localStorage.removeItem("refreshToken");
    setAccessToken(null);

    try {
      const response = await registerUser(data);
      if (response.status >= 200 && response.status < 300) {
        toast.success("Registration completed. Please login.");
        navigate("/login");
      } else {
        toast.error("Unable to register. Please try again");
      }
    } catch (error) {
      if (error.response?.data?.detail === "Email already registered with us") {
        toast.error("Email already registered. Try logging in.");
      } else {
        toast.error("Unable to register. Please try again.");
      }
    }
  };

  //   return (
  //     <div className="register-container">
  //       <div className="row">
  //         <div className="col-sm-9 col-md-7 col-lg-5 mx-auto">
  //           <div className="card border-0 shadow rounded-3 my-5" >
  //             <div className="card-body p-4 p-sm-5" >
  //               <h5 className="card-title text-center mb-5 fw-light fs-5">
  //                 Sign Up
  //               </h5>
  //               <form onSubmit={onSubmitHandler}>
  //                 <div className="form-floating mb-3">
  //                   <input
  //                     type="text"
  //                     className="form-control"
  //                     id="floatingName"
  //                     placeholder="Jhon Doe"
  //                     name="name"
  //                     onChange={onChangeHandler}
  //                     value={data.name}
  //                     required
  //                   />
  //                   <label htmlFor="floatingName">Full Name</label>
  //                 </div>
  //                 <div className="form-floating mb-3">
  //                   <input
  //                     type="email"
  //                     className="form-control"
  //                     name="userEmail"
  //                     placeholder="name@example.com"
  //                     value={data.userEmail}
  //                     onChange={(e) =>
  //                       setData({ ...data, userEmail: e.target.value })
  //                     }
  //                     required
  //                   />
  //                   <label htmlFor="floatingInput">Email</label>
  //                 </div>
  //                 <div className="form-floating mb-3">
  //                   <input
  //                     type="password"
  //                     className="form-control"
  //                     name="userPassword"
  //                     placeholder="Password"
  //                     value={data.userPassword}
  //                     onChange={(e) =>
  //                       setData({ ...data, userPassword: e.target.value })
  //                     }
  //                     required
  //                   />

  //                   <label htmlFor="floatingPassword">Password</label>
  //                 </div>

  //                 <div className="d-grid">
  //                   <button
  //                     className="btn btn-outline-primary btn-login text-uppercase"
  //                     type="submit"
  //                   >
  //                     Sign up
  //                   </button>
  //                   {/* <button
  //                     className="btn btn-outline-danger btn-login text-uppercase mt-2"
  //                     type="reset"
  //                   >
  //                     Reset
  //                   </button> */}
  //                 </div>
  //                 <div className="mt-4">
  //                   Already have an account? <Link to="/login">Sign In</Link>
  //                 </div>
  //               </form>
  //             </div>
  //           </div>
  //         </div>
  //       </div>
  //     </div>
  //   );
  // };
  return (
    <div className="register-container">
      <div className="row">
        <div className="col-sm-9 col-md-7 col-lg-5 mx-auto">
          <div className="card border-0 shadow rounded-3 my-5">
            <div className="card-body p-4 p-sm-5">
              <h5 className="card-title text-center mb-5 fw-light fs-5">
                Sign Up
              </h5>

              <form onSubmit={onSubmitHandler}>

                {/* Email */}
                <div className="form-floating mb-3">
                  <input
                    type="email"
                    className="form-control"
                    id="userEmail"
                    placeholder="name@example.com"
                    name="userEmail"
                    value={data.userEmail}
                    onChange={onChangeHandler}
                    required
                  />
                  <label htmlFor="userEmail">Email</label>
                </div>

                {/* Password */}
                <div className="form-floating mb-3">
                  <input
                    type="password"
                    className="form-control"
                    id="userPassword"
                    placeholder="Password"
                    name="userPassword"
                    value={data.userPassword}
                    onChange={onChangeHandler}
                    required
                  />
                  <label htmlFor="userPassword">Password</label>
                </div>

                {/* Submit Button */}
                <div className="d-grid">
                  <button
                    className="btn btn-outline-primary btn-login text-uppercase"
                    type="submit"
                  >
                    Sign up
                  </button>
                </div>

                <div className="mt-4">
                  Already have an account? <Link to="/login">Sign In</Link>
                </div>

              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Register;
