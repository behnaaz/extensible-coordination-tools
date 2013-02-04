
set xlabel "Counter size (#FIFOs)"
set ylabel "Linearization time (seconds)"
set yrange [0:20]
set xrange [1:30]
set terminal postscript eps color "Helvetica" 20
set output 'benchmarks.eps'

plot	"benchmarks.dat" using 1:2 title 'depth-first' with linespoints, \
	"benchmarks.dat" using 1:3 title 'breadth-first' with linespoints, \
	"benchmarks.dat" using 1:4 title 'none' with linespoints;

!epstopdf benchmarks.eps

