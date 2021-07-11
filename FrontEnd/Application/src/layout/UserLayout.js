
import React, { Component, useEffect } from "react";
import { useLocation, Route, Switch } from "react-router-dom";
import routes from "../server/router";
import Navbars from '../component/Navbars'
import { Row, Col, Container, Tabs, Tab, Card } from 'react-bootstrap'
function UserLayout() {
    const location = useLocation();
    const mainPanel = React.useRef(null);
    const getRoutes = (routes) => {
        return routes.map((prop, key) => {
            if (prop.layout === "/user") {
                return (
                    <Route
                        path={prop.layout + prop.path}
                        render={(props) => <prop.component {...props} />}
                        key={key}
                    />
                );
            } else {
                return null;
            }
        });
    };
    return (

        <div>
            <div >
                <Container className='border'>
                    <Switch>{getRoutes(routes)}</Switch>
                </Container>
            </div>
        </div>
    );
}

export default UserLayout;