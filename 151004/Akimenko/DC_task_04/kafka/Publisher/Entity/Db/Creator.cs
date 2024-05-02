﻿using Publisher.Entity.Common;
using System.ComponentModel.DataAnnotations;

namespace Publisher.Entity.Db
{
    public class creator(string login, string password, string firstName, string lastName) : AbstractEntity
    {
        [MinLength(2)]
        public string Login { get; set; } = login;
        [MinLength(8)]
        public string Password { get; set; } = password;
        [MinLength(2)]
        public string FirstName { get; set; } = firstName;
        [MinLength(2)]
        public string LastName { get; set; } = lastName;
        public ICollection<story> storys { get; set; } = [];


        public creator(int id, string login, string password, string firstName, string lastName) :
            this(login, password, firstName, lastName)
        {
            Id = id;
        }

        public creator() : this(string.Empty, string.Empty, string.Empty, string.Empty) { }
    }
}
