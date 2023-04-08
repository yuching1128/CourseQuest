import { useEffect, useState } from "react";
import Container from "react-bootstrap/Container";

export default function HomePage() {
    let [userInfo, setUserInfo] = useState({})
    useEffect(
        () => {
            setUserInfo(JSON.parse(sessionStorage.getItem("userInfo")));
        }, []
    )
    return (
        <Container className="HomePage">
            <h1>Homepage</h1>
            {Boolean(userInfo) ? (<p>Welcome {userInfo.name}!</p>) : <p>Welcome!</p>}
        </Container>
    );
}