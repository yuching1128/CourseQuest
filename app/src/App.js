import Container from 'react-bootstrap/Container';
import { HashRouter, Routes, Route, Navigate } from 'react-router-dom';
import Header from "./app/Header";
import HomePage from "./features/homepage/HomePage";
import LoginPage from "./features/login/LoginPage";
import Footer from "./app/Footer";
import { SingleCoursePage } from "./features/courses/SingleCoursePage";
import { CoursesPage } from "./features/courses/CoursesPage";
import { EditReviewForm } from "./features/reviews/EditReviewForm";
import { ProfilePage } from "./features/profile/ProfilePage";
import { MentorPage } from "./features/advising/MentorPage";
import LogoutPage from "./features/logout/LogoutPage";
import { useStore } from 'react-redux';

export default function App() {
    // const [authDetails, setAuthDetails, deleteAuthDetails] = useStore("authDetails", {});
    return (
        <Container fluid className="App">
            <HashRouter>
                <Header />
                <Routes>
                    <Route exact path="/" element={<HomePage />} />
                    <Route exact path="/login" element={<LoginPage />} />
                    <Route exact path="/logout" element={<LogoutPage />} />
                    <Route exact path="/university/:universityId/courses" element={<CoursesPage />} />
                    <Route exact path="/university/:universityId/mentor" element={<MentorPage />} />
                    <Route exact path="/university/:universityId/courses/:courseId" element={<SingleCoursePage />} />
                    <Route exact path="/university/:universityId/courses/:courseId/review/:reviewId/edit" element={<EditReviewForm />} />
                    <Route exact path="/user/:userId/profile" element={<ProfilePage />} />
                    <Route path="*" element={<Navigate to="/" />} />
                </Routes>
                <Footer />
            </HashRouter>
        </Container>
    );
}
