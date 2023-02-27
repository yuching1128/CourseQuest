import Container from "react-bootstrap/Container";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import {Image, Stack} from "react-bootstrap";
import { useForm } from "react-hook-form";

export default function LoginPage() {

    const { register, handleSubmit, formState: { errors } } = useForm();

    const onSubmit = (data) => {
        // TODO SUBMIT LOGIN DATA TO API FOR PROCESSING
        console.log(data)
    }

    return (
        <Container className="LoginPage">
            <Stack direction="horizontal" gap={3} className="LoginContent">

                <Image alt="Logo" src={require('../../images/login-page-image.png') }
                       width="400px"
                       className="d-inline-block align-top " />

                <Form onSubmit={handleSubmit(onSubmit)} className="LoginForm">
                    <h1>Login</h1>
                    <Form.Group className="mb-3" controlId="loginEmail">
                        <Form.Label>Email</Form.Label>
                        <Form.Control placeholder="Enter email" {...register("email", { required: 'Email is required' })} />
                        {errors.email && <p className="errorMessage">{errors.email?.message}</p>}
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="loginPassword" >
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" placeholder="Password"  {...register("password", { required: 'Password is required' })} />
                        {errors.password && <p className="errorMessage">{errors.password?.message}</p>}
                    </Form.Group>

                    <Button variant="primary" type="submit" className="LoginButton">Login</Button>
                </Form>
            </Stack>
        </Container>
    );
}