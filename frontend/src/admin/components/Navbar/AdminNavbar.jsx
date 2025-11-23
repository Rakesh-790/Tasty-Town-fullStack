import React from 'react';

const AdminNavbar = () => {

  const toggleSidebar = () => {
    document.body.classList.toggle("sb-sidenav-toggled");
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light border-bottom shadow-lg">
      <div className="container-fluid">
        <button className="btn" onClick={toggleSidebar}>
          <i className="bi bi-list fs-3"></i>
        </button>
        <img src="/logo.png" alt="..." height={41} width={32} />
      </div>
      <i className='bi bi-list'></i>
    </nav>
  )
}

export default AdminNavbar;