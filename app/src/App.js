import Container from 'react-bootstrap/Container';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import Header from "./app/Header";
import HomePage from "./features/homepage/HomePage";
import LoginPage from "./features/login/LoginPage";
import SignupPage from "./features/signup/SignupPage";
import Footer from "./app/Footer";
export default function App() {
    return (
        <Container fluid className="App">
            <BrowserRouter>
                <Header />
                <Routes>
                    <Route exact path="/" element={<HomePage />} />
                    <Route exact path="/login" element={<LoginPage />} />
                    <Route exact path="/signup" element={<SignupPage />} />
                    <Route path="*" element={<Navigate to="/" />} />
                </Routes>
                <Footer />
            </BrowserRouter>
        </Container>
    );
}