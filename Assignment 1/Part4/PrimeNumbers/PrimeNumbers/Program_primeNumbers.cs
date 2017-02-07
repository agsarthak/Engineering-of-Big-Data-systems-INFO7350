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

            Console.WriteLine("The 10001st prime number (press return key to generate):");
            Console.ReadLine();
            foreach (var x in primeNumbers.Skip(10000).Take(1))
            {
                Console.WriteLine(x);
            }
            Console.ReadLine();

            Console.WriteLine("Hit RETURN to build the Primonacci transformation..");
            Console.ReadLine();
            var primonacci = Unfold2(2, 3, (a, b) => a + b);
            Console.WriteLine("This is how the compiler refers to the transformation:");
            Console.WriteLine(primonacci);
            Console.WriteLine("Now that we have a transformation that describes how fibonacci numbers are built, let's generate a few..");
            Console.ReadLine();
            Console.WriteLine("1st 20 primonacci numbers (press return key to generate):");
            Console.ReadLine();
            foreach (var x in primonacci.Take(20))
            {
                Console.WriteLine(x);
            }
            Console.ReadLine();
            Console.WriteLine("The 10001st primonacci number (press return key to generate):");
            Console.ReadLine();
            foreach (var x in primonacci.Skip(10000).Take(1))
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

        private static IEnumerable<Decimal> Unfold2<T>(T seed, T seed2, Func<T, T, T> accumulator)
        {
            Boolean flag = false;
            var nextValue = seed;
            Decimal counter = 0;
            List<Decimal> primonacci = new List<Decimal>();
            int DecimalNextValue = Convert.ToInt32(seed);
            if (IsPrime(DecimalNextValue))
            {
                var a = seed;
                primonacci.Add(Decimal.Parse(a.ToString()));
                yield return Decimal.Parse(seed.ToString());
                var b = seed2;
                yield return Decimal.Parse(seed2.ToString());
                primonacci.Add(Decimal.Parse(b.ToString()));
                T c;

                while (true)
                {
                    if (flag)
                    {
                        yield return Decimal.Parse(b.ToString());
                        counter++;
                        flag = false;
                    }
                    c = b;
                    if (IsPrime(Convert.ToInt32(accumulator(a, b))))
                    {
                        b = accumulator(a, b);
                        a = c;
                        flag = true;
                    }
                    else
                    {
                        b = accumulator(a, b);
                        a = c;
                    }

                }
            }
        }

    }
}
