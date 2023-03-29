using System;

namespace PostgreAPI
{
    public class ConnectionString
    {
        private readonly string? host;
        private readonly string? database;
        private readonly string? user;
        private readonly string? password;

        public ConnectionString()
        {
            Console.Write("Host: ");
            this.host = Console.ReadLine();
            if(host == null)
                host = "DEFAULT";

            Console.Write("Database: ");
            this.database = Console.ReadLine();
            if(database == null)
                database = "DEFAULT";

            Console.Write("User: ");
            this.user = Console.ReadLine();
            if(user == null)
                user = "DEFAULT";

            Console.Write("Password: ");
            this.password = Console.ReadLine();
            if(password == null)
                password = "DEFAULT";

            Console.Clear();
        }

        public override string ToString()
        {
            return $"Host={host};Database={database};Username={user};Password={password}";
        }
    }
}