import axios from "axios"

export default class ApiService{

    static BASE_URL = "http://localhost:8080/api"

    // Get Header
     static getHeader() {
        const token = localStorage.getItem("token");
        return {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json"
        };
    }

    // Register User
    static async registerUser(registration) {
        const response = await axios.post(`${this.BASE_URL}/auth/register`, registration)
        return response.data
    }

    // Login User
    static async loginUser(loginDetails) {
        const response = await axios.post(`${this.BASE_URL}/auth/login`, loginDetails)
        return response.data
    }

    // Logout
    static logout() {
        localStorage.removeItem('token')
        localStorage.removeItem('role')
    }

    // Is Authenticated, Is Admin?, Is User?
    static isAuthenticated() {
        const token = localStorage.getItem('token')
        return !!token
    }

    static isAdmin() {
        const role = localStorage.getItem('role')
        return role === 'ADMIN'
    }

    static isUser() {
        const role = localStorage.getItem('role')
        return role === 'USER'
    }
}