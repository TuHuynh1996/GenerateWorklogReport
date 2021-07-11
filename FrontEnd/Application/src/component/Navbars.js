import { Link } from "react-router-dom";
import React, { Component } from "react";
import { useLocation } from "react-router-dom";
import {
  Navbar,
  NavDropdown,
  Nav,
  Form,
  Button,
  FormControl,
} from "react-bootstrap";
import { translate, Trans, useTranslation } from "react-i18next";

const Navbars = () => {
  const { t, i18n } = useTranslation();

  const changeLanguage = (lng) => {
    i18n.changeLanguage(lng);
  };

  return (
    <Navbar bg="light" expand="lg">
      <Navbar.Brand href="#home">React-Bootstrap</Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="mr-auto">
          <Nav.Link href="#home">{t("home")}</Nav.Link>
          <Nav.Link href="/user/chart">
            <li>{t("chart")}</li>
          </Nav.Link>
          {/* <Nav.Link href="/user/shop">
                        <li>Shop</li>
                    </Nav.Link>
                    <Nav.Link href="/user/about">
                        <li>About</li>
                    </Nav.Link> */}
          <NavDropdown title={t("language")} id="basic-nav-dropdown">
            <NavDropdown.Item onClick={() => changeLanguage("en")}>
              {t("English")}
            </NavDropdown.Item>
            <NavDropdown.Divider />
            <NavDropdown.Item onClick={() => changeLanguage("ja")}>
              {t("Japanese")}
            </NavDropdown.Item>
          </NavDropdown>
        </Nav>
        <Form inline>
          <FormControl type="text" placeholder="Search" className="mr-sm-2" />
          <Button variant="outline-success">Search</Button>
        </Form>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default Navbars;
