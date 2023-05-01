import { Route, Redirect, useLocation, Navigate } from 'react-router-dom';

export const PrivateRoute = ({ component, ...rest }) => {
    const isLoggedIn = localStorage.getItem('access_token') !== null;
    console.log(rest)
    const location = useLocation();
    return isLoggedIn ? (
        <>{rest.children}</>
    ) : (
        <Navigate
            replace={true}
            to="/login"
            state={{ from: `${location.pathname}${location.search}` }}
        />
    )

}
