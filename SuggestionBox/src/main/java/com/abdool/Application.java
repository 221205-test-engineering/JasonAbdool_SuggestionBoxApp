package com.abdool;

import io.javalin.Javalin;

public class Application {
    public static void main(String[] args) {
        Javalin app =Javalin.create();

        app.get("/form", (ctx) -> {
            String suggestion =
                    "<h1>Suggestion Form</h1>" +
                    " <form method=\"POST\" action=\"http://localhost:8080/submit\">" +
                    "<div>" +
                    "<label for=\"suggestion\">Please provide a game character name that you want to add into the game below:</label>" +
                    "<br>" +
                    "<input placeholder=\"Character Name\" type=\"text\" name=\"suggestion\" />" +
                    "</div>" +
                    "<div>" +
                    "<p>How highly do you want to see this character?</p>" +
                    "<input type=\"radio\" name=\"priority\" value=\"high\" id=\"high\">" +
                    "<label for=\"high\">High</label>" +
                    "<input type=\"radio\" name=\"priority\" value=\"medium\" id=\"medium\">" +
                    "<label for=\"medium\">Medium</label>" +
                    "<input type=\"radio\" name=\"priority\" value=\"low\" id=\"low\">" +
                    "<label for=\"low\">Low</label>" +
                    "</div>" +
                    "<button>Submit</button>" +
                    "</form>";


            ctx.html(suggestion);
        });

        app.get("/view", (ctx) -> {
            String suggestionTable = "<h1>Characters Suggested</h1>" +
                    "<a href=\"/form\">Go back to suggestion form</a>" +
                    "<table>" +
                    "<thead>" +
                    "<tr>" +
                    "<th>Suggestion</th>" +
                    "<th>Priority</th>" +
                    "</tr>" +
                    "</thead>" +
                    "<tbody>";

            Utility u = new Utility();
            String[][] suggestionList = u.getAllEntries();

            for(String[] entry : suggestionList) {
                suggestionTable += "<tr>";
                for (String element : entry) { // Add name of character + priority
                    suggestionTable += "<td>" + element + "</td>";
                }

                suggestionTable += "</tr>";
            }

            suggestionTable += "</tbody></table>";

            ctx.html(suggestionTable);
        });

        app.post("/submit", (ctx) -> {
            String suggestion = ctx.formParam("suggestion");
            String priority = ctx.formParam("priority");

            System.out.println(suggestion);
            System.out.println(priority);

            Utility u = new Utility();
            u.persist(suggestion,priority);

            ctx.redirect("/view");
        });


        app.start(8080);
    }
}
