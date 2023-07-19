--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1 (Debian 15.1-1.pgdg110+1)
-- Dumped by pg_dump version 15.1 (Debian 15.1-1.pgdg110+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: book; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.book (
    book_id character varying(255) NOT NULL,
    author character varying(255),
    borrowed_at timestamp without time zone,
    borrowed_by character varying(255),
    publish_date date,
    publisher character varying(255),
    returned_at timestamp without time zone,
    status integer,
    title character varying(255),
    total_page character varying(255)
);


ALTER TABLE public.book OWNER TO admin;

--
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.book (book_id, author, borrowed_at, borrowed_by, publish_date, publisher, returned_at, status, title, total_page) FROM stdin;
A0001	J.K Rowling	\N	\N	1997-06-29	Gramedia	\N	0	Harry Potter dan Batu Bertuah	384
\.


--
-- Name: book book_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (book_id);


--
-- PostgreSQL database dump complete
--

