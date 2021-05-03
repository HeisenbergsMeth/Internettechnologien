<%@ page import="java.util.ArrayList" %>
<%@ page import="de.falkmarinov.Internettechnologien.model.Category" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="de">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link href="css/admin.css" rel="stylesheet">

    <title>Admin</title>
</head>
    <body>
        <header class="bg-light text-center">
            <div class="d-inline text-center">
                <a href="">
                    <img class="my-3 logo-size" src="assets/logo.svg" alt="LOGO">
                </a>
            </div>
        </header>
        <% if(request.getAttribute("message") != null) { %>
            <!-- Alert -->
            <div class="alert alert-info w-100 text-center" role="alert">
                <%= request.getAttribute("message") %>
            </div>
        <% } %>
        <main class="container d-flex justify-content-center">
            <!-- Form: Book -->
            <form method="post" action="admin" class="mt-5 w-50">
                <div class="row">
                    <div class="form-outline mb-4 col">
                        <input type="text" id="title" class="form-control" name="title" required/>
                        <label class="form-label" for="title">Titel</label>
                    </div>
                    <div class="form-outline mb-4 col">
                        <input type="text" id="author" class="form-control" name="author" required/>
                        <label class="form-label" for="author">Autor</label>
                    </div>
                </div>
                <div class="row">
                    <div class="form-outline mb-4 col">
                        <input type="text" id="isbn" class="form-control" name="isbn" required/>
                        <label class="form-label" for="isbn">ISBN</label>
                    </div>

                    <div class="form-outline mb-4 col">
                        <input type="text" id="price" class="form-control" name="price" required/>
                        <label class="form-label" for="price">Preis</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="row">
                            <div class="form-outline mb-4">
                                <input type="date" id="date" class="form-control" name="date" required/>
                                <label class="form-label" for="date">Erscheinungsdatum</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-outline mb-4">
                                <input type="number" id="edition" class="form-control" value="1" min="1" name="edition" required/>
                                <label class="form-label" for="edition">Auflage</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-outline mb-4">
                                <input type="text" id="company" class="form-control" name="company" required/>
                                <label class="form-label" for="company">Verlag</label>
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <div class="mb-4">
                            <%
                                List<Category> categories = (ArrayList<Category>) config.getServletContext().getAttribute("categories");
                                for (Category category : categories) {
                            %>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" name="categories" value="<%= category.getId() %>" id="<%= category.getId() %>">
                                        <label class="form-check-label" for="<%= category.getId() %>"><%= category.getName() %></label>
                                    </div>
                            <%
                                }
                            %>
                        </div>
                    </div>
                </div>
                <div class="col mb-4">
                    <textarea class="form-control" placeholder="Beschreibung" id="description" name="description"></textarea>
                    <label for="description"></label>
                </div>
                <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                    Kategorie hinzufügen
                </button>
                <button type="submit" class="btn btn-primary btn-block">Buch hinzufügen</button>
            </form>
            <!-- Modal: Category-->
            <form method="post" action="admin">
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Kategorie hinzufügen</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="form-outline mb-4 col">
                                    <input type="text" id="newCategory" class="form-control" name="newCategory" required/>
                                    <label class="form-label" for="newCategory">Bezeichnung</label>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Zurück</button>
                                <button type="submit" class="btn btn-primary">Hinzufügen</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </main>
        <footer>

        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
                crossorigin="anonymous"></script>
    </body>
</html>
