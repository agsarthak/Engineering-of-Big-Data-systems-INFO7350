using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PrimeNumbers
{
    class Program
    {
        static void Main(string[] args)
        {
            var primeNumbers = Unfold(2, i => (i + 1));
            Console.WriteLine("This is how the compiler refers to the transformation:");
            Console.WriteLine(primeNumbers);
            Console.WriteLine("Now that we have a transformation that describes how natural numbers are built, let's generate a few..");
            Console.ReadLine();
            Console.WriteLine("Hit RETURN to generate the first 10 prime numbers..");
            Console.ReadLine();
            foreach (var x in primeNumbers.Take(10))
            {
                Console.WriteLine(x);
            }
            Console.ReadLine();
        }
        private static IEnumerable<T> Unfold<T>(T seed, Func<T, T> accumulator)
        {
            var nextValue = seed;
            while (true)
            {
                    bool prime = IsPrime(Convert.ToInt32(nextValue));
                    if (prime)
                    {
                        yield return nextValue;
                        nextValue = accumulator(nextValue);
                    }
                else
                {
                    nextValue = accumulator(nextValue);
                }
            }
        }

        public static bool IsPrime(int number)
        {
            int boundary = (int)Math.Floor(Math.Sqrt(number));

            if (number == 1) return false;
            if (number == 2) return true;

            for (int i = 2; i <= boundary; ++i)
            {
                if (number % i == 0) return false;
            }

            return true;
        }
    }
}
