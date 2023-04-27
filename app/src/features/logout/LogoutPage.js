import Container from "react-bootstrap/Container";
import { useEffect, useState } from "react";

export default function LogoutPage() {

    let [userInfo, setUserInfo] = useState({})
    useEffect(
        () => {
            setUserInfo(JSON.parse(localStorage.getItem("userInfo")));
        }
    )

    return (
        <Container className="LogoutPage">
            <h1>Logout page</h1>
        </Container>
    );
}