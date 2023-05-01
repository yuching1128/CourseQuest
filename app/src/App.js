import Container from 'react-bootstrap/Container';
import { HashRouter, Routes, Route, Navigate } from 'react-router-dom';
import Header from "./app/Header";
import HomePage from "./features/homepage/HomePage";
import LoginPage from "./features/login/LoginPage";
import Footer from "./app/Footer";
import { CoursePage } from "./features/courses/CoursePage";
import { CoursesPage } from "./features/courses/CoursesPage";
import { EditReviewForm } from "./features/reviews/EditReviewForm";
import { ProfilePage } from "./features/profile/ProfilePage";
import { MentorPage } from "./features/advising/MentorPage";
import LogoutPage from "./features/logout/LogoutPage";
import { MenteePage } from "./features/advising/MenteePage";
import { AppointmentPage } from "./features/advising/AppointmentPage";

export default function App() {
    return (
        <Container fluid className="App">
            <HashRouter>
                <Header />
                <Routes>
                    <Route exact path="/login" element={<LoginPage />} />
                    <Route exact path="/" element={<HomePage />} />
                    <Route exact path="/logout" element={<LogoutPage />} />
                    <Route exact path="/university/:universityId/courses" element={<CoursesPage />} />
                    <Route exact path="/university/:universityId/mentor" element={<MentorPage />} />
                    <Route exact path="/university/:universityId/mentee" element={<MenteePage />} />
                    <Route exact path="/advising/appointment/:appointmentId" element={<AppointmentPage />} />
                    <Route exact path="/university/:universityId/courses/:courseId" element={<CoursePage />} />
                    <Route exact path="/university/:universityId/courses/:courseId/review/:reviewId/edit" element={<EditReviewForm />} />
                    <Route exact path="/user/profile" element={<ProfilePage />} />
                    <Route path="*" element={<Navigate to="/" />} />
                </Routes>
                <Footer />
            </HashRouter>
        </Container>
    );
}
